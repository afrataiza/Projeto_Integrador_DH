package com.br.digital_hoteis.domain.repository;

import com.br.digital_hoteis.domain.entity.Guest;
import com.br.digital_hoteis.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID>, JpaSpecificationExecutor<Reservation> {
    @Query("SELECT r FROM Reservation r WHERE r.id = :id")
    Reservation findReservationById(UUID id);

    @Modifying
    @Query("UPDATE Reservation r SET " +
            "r.check_in_date = CASE WHEN :check_in_date IS NULL THEN r.check_in_date ELSE :check_in_date END, " +
            "r.check_out_date = CASE WHEN :check_out_date IS NULL THEN r.check_out_date ELSE :check_out_date END, " +
            "r.requests = CASE WHEN :requests IS NULL THEN r.requests ELSE :requests END, " +
            "r.is_canceled = :is_canceled " +
            "WHERE r.id = :id")
    void updateReservationById(
            @Param("id") UUID id,
            @Param("check_in_date") LocalDate check_in_date,
            @Param("check_out_date") LocalDate check_out_date,
            @Param("requests") String requests,
            @Param("is_canceled") boolean is_canceled);

    @Modifying
    @Query("DELETE FROM Reservation r WHERE r.id = :id")
    void deleteReservationById(@Param("id") UUID id);

    @Query("SELECT r FROM Reservation r WHERE r.guest = :guest " +
            "AND r.check_in_date <= :check_out_date " +
            "AND r.check_out_date >= :check_in_date")
    Optional<Reservation> findByGuestAndDateRange(@Param("guest") Guest guest,
                                                  @Param("check_in_date") LocalDate check_in_date,
                                                  @Param("check_out_date") LocalDate check_out_date);

    @Query("SELECT r FROM Reservation r WHERE r.guest.id = :guestId")
    Page<Reservation> findReservationsByGuestId(@Param("guestId") UUID guestId, Pageable pageable);
}
