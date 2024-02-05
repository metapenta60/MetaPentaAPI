package com.uniandes.metapenta.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AsciiFaceService {
    public List<String> getAsciiFaces() {
        return Arrays.asList(
                "( ͡° ͜ʖ ͡°)",
                "¯\\_(ツ)_/¯",
                "(•_•)",
                "(ง'̀-'́)ง"
        );
    }
}