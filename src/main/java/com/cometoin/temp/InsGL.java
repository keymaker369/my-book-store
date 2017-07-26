package com.cometoin.temp;

import io.webfolder.ui4j.api.browser.BrowserEngine;
import io.webfolder.ui4j.api.browser.BrowserFactory;
import io.webfolder.ui4j.api.browser.Page;
import io.webfolder.ui4j.api.dom.Document;
import io.webfolder.ui4j.api.dom.Element;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InsGL {

    public static void main(String[] args) throws Exception {

        String instLink = args[0];
        String exportFolderPath = args[1];
        
        String[] instLinkSplited = instLink.split("/");
        String folderId = instLinkSplited[(instLinkSplited.length - 1)];

        List<String> picturePageUrls = getPicturePageUrls(instLink, instLinkSplited);
//        List<String> picturePageUrls = FileUtils.readLines(new File("d:\\fajl.txt"), (Charset) null);

        FileUtils.writeLines(new File(exportFolderPath + folderId + ".txt"), picturePageUrls);

        downloadFiles(picturePageUrls, exportFolderPath, folderId);

    }

    private static List<String> getPicturePageUrls(String instLink, String[] instLinkSplited) throws Exception {
        String sourceHost = instLinkSplited[0] + "/" + instLinkSplited[1] + "/" + instLinkSplited[2];

        BrowserEngine browser = BrowserFactory.getWebKit();

        Page page = browser.navigate(instLink);
        page.show();
        Document doc = page.getDocument();
        List<Element> elements = doc.queryAll("._myci9");

        Thread.sleep(10);

        Optional<Element> loadMoreButton = doc.query("._8imhp._glz1g");
        if (loadMoreButton.isPresent() ) {
            loadMoreButton.get().click();
        }

        elements = doc.queryAll("._myci9");

        int attemptsLeft = 3;

        while (attemptsLeft != 0) {
            page.show();
            page.executeScript("window.scrollTo(0,document.body.scrollHeight)");

            int currentAmount = elements.size();
            elements = doc.queryAll("._myci9");
            int newAmount = elements.size();

            if(currentAmount == newAmount) {
                attemptsLeft--;
            }

            System.out.println("Number of element: " + elements.size());
            Thread.sleep(1000);
//            if(elements.size() > 3) break;
        }

        List<String> picturePageUrls = new ArrayList<>();

        for (Element picTriples : elements) {
            for (Element picUrl : picTriples.queryAll("div._8mlbc._vbtk2._t5r8b a")) {
                Optional<String> url = picUrl.getAttribute("href");
                if (url.isPresent())
                    picturePageUrls.add(sourceHost + url.get());
            }
        }

        browser.shutdown();

        return picturePageUrls;
    }

    private static void downloadFiles(List<String> picturePageUrls,String exportFolderPath, String folderId) throws Exception {
        String outputFolderPath = exportFolderPath + folderId + "\\";
        File dir = new File(outputFolderPath);
        FileUtils.forceMkdir(dir);
        List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, null);

        InsGP insGP = new InsGP();

        for (String picturePageUrl: picturePageUrls) {

            String fileId = InstUtil.getFileId(picturePageUrl);

            if(!isFilePresent(fileId, files)) {
                insGP.downloadFile(picturePageUrl, outputFolderPath);
            }
        }
    }

    private static boolean isFilePresent(String fileId, List<File> files) throws Exception {
        for (File file : files){
            if (file.getName().contains(fileId))
                return true;
        }
        return false;
    }
}
