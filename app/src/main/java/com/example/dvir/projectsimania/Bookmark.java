package com.example.dvir.projectsimania;

/**
 * Created by dvir on 12/6/2015.
 */
public class Bookmark {
    private String label;
    private String masechet;
    private String page;
    private int line;

    public Bookmark(){}

    public Bookmark(String _masechet, String _page) {
        this.label = "";
        this.masechet = _masechet;
        this.page = _page;
        this.line = 0;
    }

    public Bookmark(String _label, String _masechet, String _page) {
        this.label = _label;
        this.masechet = _masechet;
        this.page = _page;
        this.line = 0;
    }

    public Bookmark(String _masechet, String _page, int _line) {
        this.masechet = _masechet;
        this.page = _page;
        this.line = _line;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {

        return label;
    }

    public String getMasechet()
    {
        return masechet;
    }

    public String getPage()
    {
        return page;
    }

    public int getLine()
    {
        return line;
    }

    public void setMasechet(String Name)
    {
        this.masechet = masechet;
    }

    public void setPage(String page)
    {
        this.page = page;
    }
}

