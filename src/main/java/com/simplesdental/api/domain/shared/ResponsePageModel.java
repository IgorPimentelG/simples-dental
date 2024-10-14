package com.simplesdental.api.domain.shared;

import java.util.List;

public record ResponsePageModel<T>(
  long totalItems,
  int currentPage,
  int totalPages,
  List<T> items
) {}
