package io.codewith.gxstar;

import java.util.Objects;
import java.util.StringJoiner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 04/09/2022
 */

@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Message message = (Message) o;

        return Objects.equals(getText(), message.getText());
    }

    @Override
    public int hashCode() {
        return getText() != null ? getText().hashCode() : 0;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Message.class.getSimpleName() + "{", "}")
                .add("\"text\": \"" + text + "\"")
                .toString();
    }
}
