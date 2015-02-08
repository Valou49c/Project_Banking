package com.iut.couchut.banking.web.helper;

import com.google.common.base.Splitter;
import org.springframework.web.context.request.WebRequest;

import java.util.Iterator;

/**
 * Created by Valentin on 08/02/2015.
 */
public class HttpHelper {

    public static String getUri(WebRequest request) {
        Splitter splitter = Splitter.on("=").limit(2);
        Iterable<String> split = splitter.split(request.getDescription(false));
        Iterator<String> splitIterator = split.iterator();
        splitIterator.next(); // skip "uri" attribute
        return splitIterator.next();
    }


}
