package com.test.grumpytest.widgets;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class Posts {

    public ElementsCollection elements() {
        return $$(".post");
    }

    public Post getPost(Integer index) {
       return new Post(this.elements().get(index).attr("data-id"));
    }
}
