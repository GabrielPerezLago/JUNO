
import 'package:flutter/material.dart';

final ElevatedButtonThemeData junoElevatedButtonTheme = ElevatedButtonThemeData(
  style: ElevatedButton.styleFrom(
     padding: EdgeInsets.symmetric(
          vertical: 20,
          horizontal: 10
        ),
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadiusGeometry.circular(50) // Pills
        )
  )
);