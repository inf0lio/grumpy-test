package com.test.grumpytest.mode.widgets;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class Posts {

    private ElementsCollection elements() {
        return $$(".post");
    }

    public Post get(Integer index) {
       return new Post(this.elements().get(index).attr("data-id"));
    }
}
