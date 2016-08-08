package ru.nlp_project.story_line2.token;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

public class SentenceDetector {
  private SentenceDetectorME sentenceDetector;

  protected SentenceDetector() {

  }

  public static SentenceDetector newInstance(InputStream modelIn)
      throws IOException {
    SentenceDetector result = new SentenceDetector();
    result.initialize(modelIn);
    return result;
  }

  protected void initialize(InputStream modelIn) throws IOException {
    try {
      SentenceModel sentenceModel = new SentenceModel(modelIn);
      sentenceDetector = new SentenceDetectorME(sentenceModel);
    } finally {
      if (modelIn != null)
        IOUtils.closeQuietly(modelIn);
    } // try {
  }

  public void process(String text, ISentenceDetectorListener listener) {
    Span[] sentPosDetect = sentenceDetector.sentPosDetect(text);
    for (Span s : sentPosDetect)
      listener.detectSentence(s.getStart(), s.getEnd());
  }

}
