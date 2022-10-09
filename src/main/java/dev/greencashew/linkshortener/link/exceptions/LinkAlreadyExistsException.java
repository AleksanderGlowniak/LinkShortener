package dev.greencashew.linkshortener.link.exceptions;

public class LinkAlreadyExistsException extends  RuntimeException {
    public LinkAlreadyExistsException(final String id) {
        super("Link already exist");
    }
}
