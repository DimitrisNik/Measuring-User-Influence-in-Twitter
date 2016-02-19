package paper3;

import cc.mallet.pipe.*;
import cc.mallet.pipe.iterator.*;
import cc.mallet.topics.*;
import cc.mallet.types.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class Topic {

    int numTopics = 1;
    double alpha_t = 1.0;
    double beta_w = 0.01;
    int num_threads = 2;
    int NumIterations = 1000;
    String stopWordsPath = "C:\\Users\\Dimitris\\Documents\\NetBeansProjects\\Mesuring Influence\\el.txt";

    private Reader readfile(String path) throws FileNotFoundException {
        File file = new File(path);
        FileInputStream fi = new FileInputStream(file);
        InputStreamReader ir = new InputStreamReader(fi);
        Reader fileReader = ir;

        return fileReader;
    }

    private InstanceList getPipeline(Reader fileReader) {
        // Begin by importing documents from text to feature sequences
        ArrayList<Pipe> pipeList = new ArrayList<Pipe>();

        // Pipes: lowercase, tokenize, remove stopwords, map to features
        pipeList.add(new CharSequenceLowercase());
        pipeList.add(new CharSequence2TokenSequence(Pattern.compile("\\p{L}[\\p{L}\\p{P}]+\\p{L}")));
        pipeList.add(new TokenSequenceRemoveStopwords(new File(stopWordsPath), "UTF-8", false, false, false));
        pipeList.add(new TokenSequence2FeatureSequence());

        InstanceList instances = new InstanceList(new SerialPipes(pipeList));
        instances.addThruPipe(new CsvIterator(fileReader, Pattern.compile("[\\s,]*(.*)$"), 1, 1, 1));

        return instances;
    }

    private ParallelTopicModel getmodel(InstanceList instances) throws IOException {
        // Create a model with 100 topics, alpha_t = 0.01, beta_w = 0.01
        ParallelTopicModel model = new ParallelTopicModel(numTopics, alpha_t, beta_w);

        model.addInstances(instances);
        model.setNumThreads(num_threads);

        model.setNumIterations(NumIterations);
        model.estimate();

        return model;
    }

    public ArrayList<String> getTopic(String path) throws FileNotFoundException, IOException {
        ArrayList<String> topicsList = new ArrayList<>();

        Reader fileReader = readfile(path);
        InstanceList instances = getPipeline(fileReader);
        ParallelTopicModel model = getmodel(instances);

        Alphabet dataAlphabet = instances.getDataAlphabet();
        ArrayList<TreeSet<IDSorter>> topicSortedWords = model.getSortedWords();

        for (int topic = 0; topic < numTopics; topic++) {
            Iterator<IDSorter> iterator = topicSortedWords.get(topic).iterator();
            while (iterator.hasNext()) {
                IDSorter idCountPair = iterator.next();
                topicsList.add(dataAlphabet.lookupObject(idCountPair.getID()).toString());
            }

        }
        return removeDublicates(topicsList);

    }

    private ArrayList<String> removeDublicates(ArrayList<String> list) {
        HashSet hs = new HashSet();
        hs.addAll(list);
        list.clear();
        list.addAll(hs);
        return list;
    }

}
