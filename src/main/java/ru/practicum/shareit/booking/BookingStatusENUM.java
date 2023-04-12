package ru.practicum.shareit.booking;

public enum BookingStatusENUM {
    WAITING,  // — новое бронирование, ожидает одобрения,
    APPROVED, // — Дополнительные советы ментора2бронирование подтверждено владельцем,
    REJECTED, // — бронирование отклонено владельцем,
    CANCELED // — бронирование отменено создателем
}
