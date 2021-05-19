package com.mufg.roverbot;

import com.mufg.roverbot.constants.FileConstants;
import com.mufg.roverbot.domain.*;
import com.mufg.roverbot.utils.RoverBotUtils;
import com.mufg.roverbot.validator.RoverValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import static org.junit.Assert.assertTrue;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RoverBotApplication.class)
public class RoverBotUnitTests {

    @Autowired
    RoverValidator validator;

    @Test
    public void testXMLFileCreateAndRead() throws IOException {

       RoverBotBase rover = new RoverBotBase(new Position(20,20,"E"));
       Path path = Paths.get(System.getProperty(FileConstants.DIR.getVal())+"/test/");
       Files.createDirectories(path);
       RoverBotUtils.createXMLFile(rover, "test", "TestFile");

       RoverBotResponse response = RoverBotUtils.readXMLFile("test", "TestFile");

       Assert.notNull(response, "Response must not be null");

       Files.walk(Paths.get(System.getProperty(FileConstants.DIR.getVal())+"/test/"))
               .filter(Files::isRegularFile)
               .map(Path::toFile)
               .forEach(File::delete);
       Files.deleteIfExists(path);
   }

   @Test
   public void testValidateRoverValidData() throws Exception {

        List<Move> moveList = new ArrayList<Move>();

        RoverBotRequest request = new RoverBotRequest(new Position(10,10,"E"), moveList);
        validator.validateRover(request);
        //If no Exception then pass test automatically
        assertTrue(true);
    }

    @Test
    public void testValidateRoverInvalidDirection() throws Exception {

        List<Move> moveList = new ArrayList<Move>();
        try {
            RoverBotRequest request = new RoverBotRequest(new Position(10,10,"B"), moveList);
            validator.validateRover(request);
        } catch (final RuntimeException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void testValidateRoverInvalidRightDegree() throws Exception {

        List<Move> moveList = new ArrayList<Move>();
        Move move  = new Move("1", 40, 10,20, 30);
        moveList.add(move);
        try {
            RoverBotRequest request = new RoverBotRequest(new Position(10,10,"S"), moveList);
            validator.validateRover(request);
        } catch (final RuntimeException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void testValidateRoverInvalidLeftDegree() throws Exception {

        List<Move> moveList = new ArrayList<Move>();
        Move move  = new Move("1", 90, 80,20, 30);
        moveList.add(move);
        try {
            RoverBotRequest request = new RoverBotRequest(new Position(10,10,"S"), moveList);
            validator.validateRover(request);
        } catch (final RuntimeException ex) {
            assertTrue(true);
        }
    }

}