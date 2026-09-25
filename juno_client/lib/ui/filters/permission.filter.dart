import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:intl/date_symbols.dart';
import 'package:juno_client/domain/seession/SESSION.dart';
import 'package:juno_client/domain/types/UserType.dart';

class PermissionEnrouter {
  static final SESSION _session = SESSION.instance;
  
  // ignore: unused_field
  static final _routerMapper = {
    '/home': {
      UserType.USUARIO : '/home-user'
    }
  };


  static void go(BuildContext context, String viewRoute) {
    for (final entries in _routerMapper.entries) {
      if (entries.key == viewRoute) {
        for (final routes in entries.value.entries) {
          if (routes.key.valueOf(_session.rol!)) context.go(routes.value);
        }
      }
    }
  }

}
