package com.daio.api.filehandler;

import com.daio.api.jsonconverter.Tuple;
import com.google.inject.Inject;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileNameSupplierImpl implements FileNameSupplier {

    private final String directory;

    @Inject
    public FileNameSupplierImpl(String directory) {
        this.directory = directory;
    }

    @Override
    public Map<String, List<String>> get() {
        final File files = new File(directory);
        return Stream.of(Objects.requireNonNull(files.listFiles()))
                .filter(file -> !file.getName().equals(".DS_Store"))
                .map(file -> new Tuple<>(file.getName(), Arrays.asList(Objects.requireNonNull(file.list()))))
                .collect(Collectors.toMap(Tuple::getLeft, Tuple::getRight));
    }

}

