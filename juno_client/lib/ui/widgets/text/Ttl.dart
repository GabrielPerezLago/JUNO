import 'package:flutter/material.dart';

class Ttl extends StatelessWidget {
    String text;
    
    Ttl({
      super.key, 
      required this.text
    });

    @override
  Widget build(BuildContext context) => Text(
    text, 
    
  );

}