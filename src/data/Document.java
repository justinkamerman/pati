/**
 * $Id$ 
 *
 * $LastChangedDate$ 
 * 
 * $LastChangedBy$
 */
package data;

import java.util.logging.Logger;

public class Document
{
    private static Logger log = Logger.getLogger (Document.class.getName());
    private int __id = 0;
    private String __content;
    private String __author;
    private String __title;
    private String __link;


    public Document () {}
    public Document (int id, String content, String author, String title, String link)
    {
        __id = id;
        __content = content;
        __author = author;
        __title = title;
        __link = link;
    }


    public Document (String content, String author, String title, String link)
    {
        __content = content;
        __author = author;
        __title = title;
        __link = link;
    }


    public int getId () { return __id; }
    public void setId (int id) { __id = id; }
    public String getContent () { return __content; }
    public void setContent (String content) { __content = content; }
    public String getAuthor () { return __author; }
    public void setAuthor (String author) { __author = author; }
    public String getTitle () { return __title; }
    public void setTitle (String title) { __title = title; }
    public String getLink () { return __link; }
    public void setLink (String link) { __link = link; }


    public String toString ()
    {
        return String.format ("[id=%d][author=%s][title=%s][link=%s][content=%s]",
                              __id,
                              __author,
                              __title,
                              __link,
                              __content);
    }
}
    
    
    