package com.daio.api.filehandler;

import java.util.Map;
import java.util.function.Supplier;

@FunctionalInterface
public interface FileNameSupplier extends Supplier<Map<String, String[]>> {
}
