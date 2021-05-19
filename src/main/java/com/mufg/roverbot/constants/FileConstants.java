package com.mufg.roverbot.constants;

/**
 * FileConstants holds attributes related to File handling.
 *
 * @author Kishore Diyyana
 */
public enum FileConstants {

    FILE_NAME_INPUT("RoverBotRequest"),
    FILE_NAME_OUTPUT("RoverBotResponse"),
    DIR_OUTPUT("output"),
    DIR_INPUT("input"),
    DIR("user.dir"),
    XML_FILE_TYPE(".xml"),
    UTF_FORMAT("UTF-8");

    private String value;

    FileConstants (String value) {
        this. value = value;
    }

    public String getVal() {
        return value;
    }
}
