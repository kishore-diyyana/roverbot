
package com.mufg.roverbot.utils;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mufg.roverbot.constants.FileConstants;

import com.mufg.roverbot.domain.RoverBotBase;
import com.mufg.roverbot.domain.RoverBotResponse;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

/**
 * This class is used to build util methods.
 *
 * @author Kishore Diyyana
 */
public final class RoverBotUtils {

    public static void createXMLFile(final RoverBotBase rover, final String folder, final String fileName) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xml = xmlMapper.writeValueAsString(rover);
        StringBuilder path = new StringBuilder("\\").append(folder).append("\\").append(fileName);
        //Files.createDirectories(System.getProperty(FileConstants.DIR.getVal()) + folder));
        //Create Input file with timestamp to keep for records and archive later.
        if ("input".equalsIgnoreCase(folder)) {
            path.append(Calendar.getInstance().getTimeInMillis()).append(FileConstants.XML_FILE_TYPE.getVal());
        }
        //Create Output file, always latest Rover current position state maintains in file.
        else {
            path.append(FileConstants.XML_FILE_TYPE.getVal());
        }
        Files.write(Paths.get(System.getProperty(FileConstants.DIR.getVal()) + path), xml.getBytes());
    }

    public static RoverBotResponse readXMLFile(final String folder, String fileName) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        String path = "\\" + folder + "\\" +
                fileName + FileConstants.XML_FILE_TYPE.getVal();
        File file = new File(System.getProperty(FileConstants.DIR.getVal())+ path);
        String xml = new String (Files.readAllBytes(file.toPath()),
                                 Charset.forName(FileConstants.UTF_FORMAT.getVal()));
        RoverBotResponse response = xmlMapper.readValue(xml, RoverBotResponse.class);
        return response;
    }
}
