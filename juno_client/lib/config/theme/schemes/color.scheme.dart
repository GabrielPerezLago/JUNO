import 'package:flutter/material.dart';

abstract class JunoColorScheme {

  static ColorScheme light() => ColorScheme(
    brightness: Brightness.light,
    primary: const Color(0xff6366F1),
    secondary:const Color(0xff10B981),
    onPrimary: Colors.white,
    onSecondary: Colors.white,
    tertiary: const Color(0xFFF97316),
    onTertiary: Colors.white,
    error: const Color(0xFFEF4444),
    onError: Colors.white,
    surface: const Color(0xFFF8FAFC),
    onSurface: const Color(0xFF0F172A),
  );


  static ColorScheme dark() => ColorScheme.dark(
    brightness: Brightness.dark,
    primary: const Color(0xff6366F1),
    onPrimary: Colors.black,
    secondary: const Color(0xFF34D399),
    onSecondary: Colors.black,
    tertiary: const Color(0xFFFB923C), 
    surface: const Color(0xFF0F172A),
    onSurface: const Color(0xFFF8FAFC),
  );
}