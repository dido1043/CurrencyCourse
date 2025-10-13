package org.example.currencycourseapi.repository;

import org.example.currencycourseapi.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
