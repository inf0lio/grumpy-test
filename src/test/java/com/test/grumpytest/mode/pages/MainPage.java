package com.test.grumpytest.mode.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private SelenideElement newPost = $(".title_new");

    @Step
    public MainPage open() {
        Selenide.open("/");
        this.newPost.shouldNotBe(visible);

        return this;
    }

    @Step
    public MainPage login() {
        Selenide.open("/new");
        Selenide.open("/");
        this.newPost.shouldBe(visible);

        return this;
    }

    @Step
    public DraftPage create() {
        newPost.click();

        return new DraftPage();
    }
}
