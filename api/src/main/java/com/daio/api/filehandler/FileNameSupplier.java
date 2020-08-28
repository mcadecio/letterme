package com.daio.api.filehandler;

import com.google.inject.ImplementedBy;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@FunctionalInterface
@ImplementedBy(FileNameSupplierImpl.class)
public interface FileNameSupplier extends Supplier<Map<String, List<String>>> {
}
