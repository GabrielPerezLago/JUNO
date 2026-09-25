import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

class HomeDesktopView extends StatefulWidget {
  Widget layout;
  
  HomeDesktopView({ 
    super.key, 
    required this.layout
  });


  @override
  State<StatefulWidget> createState() => _HomeDesktopState();
}


class _HomeDesktopState extends State<HomeDesktopView> {
  
  Widget get layout => widget.layout;

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      body: Center(
        child: SafeArea(
          child: SingleChildScrollView(
            child: layout,
          )
        ),
      ),
    );
  }


  

}
