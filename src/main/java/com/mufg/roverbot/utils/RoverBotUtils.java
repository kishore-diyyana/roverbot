
package com.mufg.roverbot.utils;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mufg.roverbot.constants.FileConstants;

import com.mufg.roverbot.domain.RoverBotBase;
import com.mufg.roverbot.domain.RoverBotResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * This class is used to build util methods.
 *
 * @author Kishore Diyyana
 */
public final class RoverBotUtils {

    private static Logger log = LoggerFactory.getLogger(RoverBotUtils.class);
    public static void createXMLFile(final RoverBotBase rover, final String folder, final String fileName) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xml = xmlMapper.writeValueAsString(rover);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        StringBuilder path = new StringBuilder("\\").append(folder).append("\\").append(fileName);
        Files.createDirectories(new File(System.getProperty(FileConstants.DIR.getVal()) +"\\"+ folder).toPath());
        //Create Input file with timestamp to keep for records and archive later.

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if ("input".equalsIgnoreCase(folder)) {
            path.append("-").append(sdf.format(timestamp)).append(FileConstants.XML_FILE_TYPE.getVal());
        }
        //Create Output file, always latest Rover current position state maintains in file.
        else {
            path.append(FileConstants.XML_FILE_TYPE.getVal());
        }
        Files.write(Paths.get(System.getProperty(FileConstants.DIR.getVal()) + path), xml.getBytes());
        log.debug("RoverBot createXMLFile method completed");
    }

    public static RoverBotResponse readXMLFile(final String folder, String fileName) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        String path = "\\" + folder + "\\" +
                fileName + FileConstants.XML_FILE_TYPE.getVal();
        File file = new File(System.getProperty(FileConstants.DIR.getVal())+ path);
        String xml = new String (Files.readAllBytes(file.toPath()),
                                 Charset.forName(FileConstants.UTF_FORMAT.getVal()));
        RoverBotResponse response = xmlMapper.readValue(xml, RoverBotResponse.class);
        log.debug("RoverBot readXMLFile method completed");
        return response;
    }
}
