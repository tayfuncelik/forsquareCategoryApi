/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 *
 * License:
 *
 * Licensed under GNU Lesser General Public License Version 3 or later (the "LGPL")
 * http://www.gnu.org/licenses/lgpl.html
 */

package com.example.fs.io;

/**
 * Class representing Multipart request parameter
 */
public class MultipartParameter {

    private String name;
    private String contentType;
    private byte[] content;

    public MultipartParameter(String name, String contentType, byte[] content) {
        this.name = name;
        this.contentType = contentType;
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }

    public String getName() {
        return name;
    }


}
