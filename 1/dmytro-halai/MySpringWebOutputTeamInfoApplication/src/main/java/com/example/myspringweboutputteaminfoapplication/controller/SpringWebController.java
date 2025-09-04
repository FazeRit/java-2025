package com.example.myspringweboutputteaminfoapplication.controller;

import com.example.myspringweboutputteaminfoapplication.constants.TeamInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SpringWebController {

    @GetMapping("/")
    String entryInfo() {
        return TeamInfo.ENTRY_INFO;

    }

    @GetMapping("/dmytro")
    String dmytroInfo() {
        return TeamInfo.DMYTRO_INFO;
    }

    @GetMapping("/andrii")
    String andriiInfo() {
        return TeamInfo.ANDRII_INFO;
    }

    @GetMapping("/denys")
    String denysInfo() {
        return TeamInfo.DENYS_INFO;
    }

    @GetMapping("/angelina")
    String angelinaInfo() {
        return TeamInfo.ANGELINA_INFO;
    }

    @GetMapping("/daniil")
    String daniilInfo() {
        return TeamInfo.DANIIL_INFO;
    }
}
