package com.mufg.roverbot;

import com.mufg.roverbot.domain.Move;
import com.mufg.roverbot.domain.Position;
import com.mufg.roverbot.domain.RoverBotRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * Integration tests for Rover Bot Service application.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = RoverBotApplication.class)
@TestPropertySource(properties = {"management.port=0"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RoverBotIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * Test Case: DUPLICATE_REFERENCE without mirror input
     */
    @Test
    public void shouldReturn201WhenSendingPostEventsRequestToRoverController() throws Exception {

        List<Move> moveList = new ArrayList<Move>();
        Move move1  = new Move("1", null, 90,10, null);
        Move move2  = new Move("2", 180, null,null, 20);
        moveList.add(move1);
        moveList.add(move2);
        RoverBotRequest request = new RoverBotRequest(new Position(10,10,"N"), moveList);
        testRestRoverPostService(request);
    }

    /**
     * This is common test method to call Rest Service.
     * @throws Exception
     */
    @Test
    public void shouldReturn200WhenSendingGetStatusRequestToRoverController() throws Exception {
        ResponseEntity<String> entity = testRestTemplate.getForEntity(
                "/api/roverbot-service/position", String.class);
        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        StringBuffer expected = new StringBuffer("{\"position\": {\"x\": -20,\"y\": 10,\"direction\": \"E\"}}");
        JSONAssert.assertEquals(expected.toString(), entity.getBody().toString(), false);
    }

    @Test
    public void shouldReturn200WhenSendingStatusRequestToController() throws Exception {
        ResponseEntity<String> entity = testRestTemplate.getForEntity(
                "/api/roverbot-service/status", String.class);
        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    /**
     * This is common test method to call Rest Service.
     * @param request
     * @throws Exception
     */
    private void testRestRoverPostService(RoverBotRequest request) throws Exception {
        ResponseEntity<Object> entity = this.testRestTemplate.postForEntity(
                "/api/roverbot-service/events", request, Object.class);
        then(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

}