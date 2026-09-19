import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  final Widget child;

  const HomePage({super.key, 
    required this.child
  });

  @override
  State<HomePage> createState() => _HomePageState(scene: child);

}


class _HomePageState extends State<HomePage> {
  final Widget scene;

  _HomePageState({
    required this.scene
  });

  @override
  Widget build(BuildContext context) => scene;

}