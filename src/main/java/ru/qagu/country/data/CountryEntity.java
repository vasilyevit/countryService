package ru.qagu.country.data;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;
import ru.qagu.country.model.Country;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "country")
public class CountryEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(name = "country_name", unique = true, nullable = false)
    private String countryName;

    @Column(name = "country_code", unique = true, nullable = false)
    private String countryCode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public static CountryEntity fromJson(Country country) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCountryName(country.countryName());
        countryEntity.setCountryCode(country.countryCode());
        return countryEntity;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CountryEntity that = (CountryEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

}
