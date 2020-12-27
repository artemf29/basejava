package com.core.webapp.util;

import com.core.webapp.model.Resume;
import com.core.webapp.model.Section;
import com.core.webapp.model.TextSection;
import org.junit.Assert;
import org.junit.Test;

import static com.core.webapp.model.ResumeTestData.*;
import static org.junit.Assert.*;

public class JsonParserTest {

    @Test
    public void testResume() throws Exception {
        String json = JsonParser.write(RESUME_1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(RESUME_1, resume);
    }

    @Test
    public void write() {
        Section section1 = new TextSection("TextSection1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        Assert.assertEquals(section1, section2);
    }
}