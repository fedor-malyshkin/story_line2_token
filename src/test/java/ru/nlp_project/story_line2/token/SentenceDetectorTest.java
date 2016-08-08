package ru.nlp_project.story_line2.token;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SentenceDetectorTest {

  private SentenceDetector testable;

  @Before
  public void setUp() throws Exception {
    testable = SentenceDetector.newInstance(new FileInputStream(
        "data/ru-opennlp-model-sent.bin"));
  }

  @Test
  public void testProcess() {
    final List<String> result = new ArrayList<String>(10);
    final String text = "Немного текста. И вот на русском языке. А это сможешь...  Но это была  ерунда.";
    testable.process(text, new ISentenceDetectorListener() {
      @Override
      public void detectSentence(int start, int end) {
        result.add(text.substring(start, end));
      }
    });
    assertEquals(4, result.size());
    assertEquals("Немного текста.", result.get(0));
    assertEquals("И вот на русском языке.", result.get(1));
    assertEquals("А это сможешь...", result.get(2));
    assertEquals("Но это была  ерунда.", result.get(3));
  }
}
