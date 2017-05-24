package com.cometoin.temp;

import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserFactory;
import com.ui4j.api.browser.Page;
import com.ui4j.api.dom.Document;
import com.ui4j.api.dom.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Ins {

    public static void main(String[] args) throws Exception {
        //System.setProperty("ui4j.headless", "true");

        BrowserEngine browser = BrowserFactory.getWebKit();

        Page page = browser.navigate("https://www.asdfg.com");
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
        }

        List<String> picturePageUrls = new ArrayList<>();

        for (Element picTriples : elements) {
            for (Element picUrl : picTriples.queryAll("a._8mlbc._vbtk2._t5r8b")) {
                Optional<String> url = picUrl.getAttribute("href");
                if (url.isPresent())
                    picturePageUrls.add(url.get());
            }
        }

        Util.writeToFile(picturePageUrls, "f:\\output.txt");

        browser.shutdown();
    }
}
