package dev.greencashew.linkshortener.link.api.exceptions;

public class LinkAlreadyExistsException extends  RuntimeException {
    public LinkAlreadyExistsException(final String id) {
        super("Link already exist");
    }
}
