package com.cometoin.temp;

import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserFactory;
import com.ui4j.api.browser.Page;
import com.ui4j.api.dom.Document;
import com.ui4j.api.dom.Element;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InsGP {

    private BrowserEngine browser;

    public InsGP() {
    }


    public InsGP(BrowserEngine browser) {
        this.browser = browser;
    }

    public void downloadFile(String insLink, String outputFilePath, BrowserEngine browser) throws Exception {


        String fileId = insLink.split("/")[4];

        Page page = browser.navigate(insLink);
        Document doc = page.getDocument();

        Optional<Element> img = doc.query("._jjzlb img");

        if (img.isPresent() ) {

            Optional<String> imgUrlOptional = img.get().getAttribute("src");

            if(imgUrlOptional.isPresent()) {

                String imgUrl = imgUrlOptional.get();

                FileUtils.copyURLToFile(new URL(imgUrl), new File(outputFilePath + fileId + ".jpg"));
                System.out.println("Downloaded img: " + imgUrl);

            }
        } else {
            Optional<Element> vid = doc.query("video._c8hkj");

            if (vid.isPresent() ) {

                Optional<String> vidUrlOptional = vid.get().getAttribute("src");

                if(vidUrlOptional.isPresent()) {

                    String vidUrl = vidUrlOptional.get();

                    FileUtils.copyURLToFile(new URL(vidUrl), new File(outputFilePath + fileId + ".mp4"));

                    System.out.println("Downloaded vidg: " + vidUrl);
                }
            }

        }

    }

    public void downloadFile(String insLink, String outputFilePath) throws Exception {
        if(browser == null) {
            throw new NullPointerException("Browser can't be null");
        }
        downloadFile(insLink, outputFilePath, this.browser);
    }
}
