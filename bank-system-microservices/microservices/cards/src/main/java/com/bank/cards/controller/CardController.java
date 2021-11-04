package com.bank.cards.controller;

import com.bank.cards.domain.Card;
import com.bank.cards.domain.Customer;
import com.bank.cards.repository.CardRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    @PostMapping("/myCards")
    public List<Card> getCardDetails(@RequestBody Customer customer) {
        List<Card> cards = cardRepository.findByCustomerId(customer.getId());
        if (cards != null) {
            return cards;
        } else {
            return null;
        }

    }
}
