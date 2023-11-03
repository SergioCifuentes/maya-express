package com.mayaexpress.repository;

import com.mayaexpress.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Integer> {

}
