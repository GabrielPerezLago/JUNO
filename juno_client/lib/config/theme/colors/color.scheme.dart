import 'package:flutter/material.dart';

abstract class JunoColorScheme {

  static ColorScheme light() => ColorScheme(
    brightness: Brightness.light,
    primary: const Color(0xff6366F1),
    secondary:const Color(0xff10B981),
    onPrimary: Colors.white,
    onSecondary: Colors.white,
    tertiary: const Color(0xFF14B8A6),
    onTertiary: Colors.white,
    error: const Color(0xFFEF4444),
    onError: Colors.white,
    surface: const Color(0xFFF8FAFC),
    onSurface: const Color(0xFF0F172A),
  );


  static ColorScheme dark() => ColorScheme.dark(
    brightness: Brightness.dark,
    primary: const Color(0xff6366F1),
    onPrimary: Color(0xff1F1F1F),
    secondary: const Color(0xFF34D399),
    onSecondary: Colors.black,
    tertiary: const Color(0xff14B8A61), 
    surface: const Color(0xFF1D0074),
    onSurface: const Color(0xFFF8FAFC),
  );
}