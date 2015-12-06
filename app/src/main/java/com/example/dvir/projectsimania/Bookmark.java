package com.example.dvir.projectsimania;

/**
 * Created by dvir on 12/6/2015.
 */
public class Bookmark {
    private String masechet;
    private int page;
    private int line;

    public Bookmark(){}

    public Bookmark(String _masechet, int _page){
        this.masechet = _masechet;
        this.page = _page;
        this.line = 0;
    }

    public Bookmark(String _masechet, int _page,int _line)
    {
        this.masechet = _masechet;
        this.page = _page;
        this.line = _line;
    }

    public String getMasechet()
    {
        return masechet;
    }

    public int getPage()
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

    public void setStrikes (int strikes)
    {
        this.page = strikes;
    }
}

