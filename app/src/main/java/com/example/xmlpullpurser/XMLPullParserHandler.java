package com.example.xmlpullpurser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLPullParserHandler {
    List<Student> students;
    private Student student;
    private String text;

//XMLPullParserHandler constructor to initialize the ArrayList
    public XMLPullParserHandler() {
        students = new ArrayList<Student>();
    }

    public List<Student> getStudents() {
        return students;
    }
    public List<Student> parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("student")) {
                            // create a new instance of student
                            student = new Student();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("student")) {
                            // add student object to list
                            students.add(student);
                        } else if (tagname.equalsIgnoreCase("name")) {
                            student.setName(text);
                        } else if (tagname.equalsIgnoreCase("id")) {
                            student.setId(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("department")) {
                            student.setDepartment(text);
                        } else if (tagname.equalsIgnoreCase("email")) {
                            student.setEmail(text);
                        } else if (tagname.equalsIgnoreCase("type")) {
                            student.setType(text);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }
}
