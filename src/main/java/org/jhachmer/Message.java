package org.jhachmer;

import java.io.Serializable;

public record Message(String name, String text) implements Serializable {
}
