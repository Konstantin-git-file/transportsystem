package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.generalDto.RoleDto;
import com.tutorial.transportsystem.entity.Role;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoleMapper {

  Role roleDtoToRole(RoleDto roleDto);

  RoleDto roleToRoleDto(Role role);
}
