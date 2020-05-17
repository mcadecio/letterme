package com.daio.api.filehandler;

import com.daio.api.jsonconverter.Tuple;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileNameSupplierImpl implements FileNameSupplier {

    private final String directory;

    public FileNameSupplierImpl(String directory) {
        this.directory = directory;
    }

    @Override
    public Map<String, String[]> get() {
        final File files = new File(directory);
        return Stream.of(Objects.requireNonNull(files.listFiles()))
                .map(file -> new Tuple<>(file.getName(), file.list()))
                .collect(Collectors.toMap(Tuple::getLeft, Tuple::getRight));
    }

}

