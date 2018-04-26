package org.galatea.samples.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
public class GreetingsController {

    @Value("#{'${users}'.split(',')}")
    private List<String> users;

    @RequestMapping(
            value = "/greeting",
            method = RequestMethod.GET)
    public ResponseEntity<String> whoami(@RequestParam("user") final String user) {

        if (users.contains(user)) {
            return new ResponseEntity("Hello " + user + "!", HttpStatus.OK);
        } else {
            return new ResponseEntity(user + " is not an authorized user", HttpStatus.UNAUTHORIZED);
        }
    }
}
