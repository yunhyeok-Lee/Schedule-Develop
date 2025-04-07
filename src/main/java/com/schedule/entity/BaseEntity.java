package com.schedule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
/**
 * 객체의 입장에서 공통 매핑 정보가 필요할 때 사용
 * id, name은 객체의 입장에서 볼 때 계속 나온다.
 * 이렇게 공통 매핑 정보가 필요 할 때, 부모 클래스에 선언하고 속성만 상속 받아서 사용하고 싶을 때 사용
 * DB 테이블과 상관 X
 */
@EntityListeners(AuditingEntityListener.class)
// JPA에서 엔티티의 생성 / 수정 시간 같은 걸 자동으로 기록 하고 싶을 때 사용하는 어노테이션
public abstract class BaseEntity {

    @CreatedDate
    // 엔티티가 처음 저장될 때 자동으로 시간 기록함
    @Column(updatable = false)
    // DB에 저장된 이후에는 수정 X, update 쿼리 시 이 필드는 무시
    private LocalDateTime createdAt;

    @LastModifiedDate
    // 엔티티가 수정될 때마다 자동으로 시간 갱신
    private LocalDateTime updatedAt;
}
