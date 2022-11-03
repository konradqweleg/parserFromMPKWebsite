package org.example.lineNumbersParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class DiskWebPageAccess {



    private static Document mainPage;



    private DiskWebPageAccess() throws IOException {

        String  pathWebsiteMainPageMPK=PropertiesServices.getProperties().getPropertyAsString("PATH_WEBSITE_MPK");
        File fileMainPage = new  File(pathWebsiteMainPageMPK);
        mainPage= Jsoup.parse(fileMainPage, "UTF-8", "");




    }

    public static Document GetPageFromPath(String path) throws IOException {
        path=path.split("\\?")[0];

        String  pathWebsiteMainPageMPK=PropertiesServices.getProperties().getPropertyAsString("BASE_PATH_ACESS");;
        File fileMainPage = new  File(pathWebsiteMainPageMPK+"/"+path);
        Document   doc= Jsoup.parse(fileMainPage, "UTF-8", "");
        return doc;
    }




    public static Document getMainPageDocument() throws IOException {
        if(mainPage==null){
            new DiskWebPageAccess();
        }
        return mainPage;

    }

}
