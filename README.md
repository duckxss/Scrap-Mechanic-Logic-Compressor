# Scrap Mechanic Logic Compressor

Simple to use and intuitive logic compressor for Scrap Mechanic blueprints.

## Table of Contents

- [Overview](#overview)
- [Screenshots](#screenshots)
- [Installation](#installation)
- [Usage](#usage)

---

## Overview

Ever been playing Scrap Mechanic and wished you could take your prototype logic creation and then compress it into a smaller size to install it into a vehicle? 

This simple java app takes any Scrap Mechanic blueprint file and compresses the blocks into a small cube. It is compatible with any mod and has a focus towards the fant mod logic blocks.

It features block sorting for important fand mod blocks such as counters and smart engines and sorts vanilla buttons and switches.

---

## Screenshots

Before:
![202506~3](https://github.com/user-attachments/assets/9dd3ecc2-abc3-438b-bcf4-c83d88537aa9)

After:
![202506~2](https://github.com/user-attachments/assets/a56324b6-24dc-4b29-bc84-a84b927a31c4)

## Installation

idk

## Usage

This program is made to compress Scrap Mechanic logic blocks. It however does not sort specifically for logic type blocks and so please remove any non-tileable parts from your creation before using this. It will also remove any tileable blocks. See my example above.

1. ** MAKE A BACKUP ** If you mess something up it's always nice to have a backup. I usually just make a new blueprint save within Scrap Mechanic.

2. Find your blueprint file and copy the path. These are located by default at C:\Users\yourname\AppData\Roaming\Axolot Games\Scrap Mechanic\User\User_usernumber\Blueprints\blueprint-UUID\blueprint.json. You can get to the hidden AppData folder by pressing WIN + R and typing %appdata%

   I find that saving your blueprint in Scrap Mechanic right before doing this makes it easier as that makes the file be the one at the top when sorted by date. You can also check the icon.png to double check.

   Right click on the blueprint.json file and press "Copy as Path" you will use this later.
  
4. Select your options. Within the app you will find a few checkboxes and inputs.
   
  - **Custom Bounds**
   If you leave the "Use custom bounds for compressed size" checkbox unchecked (Recommended) the program will find the smallest cube for your creation and compress it into this size. Alternatively you can input your own size parameters and the program will attempt to compress it to this size. **Putting impossible parameters may lead to bugs**
  
  - **Sorters**
    There are 3 built in sorters for different kinds of blocks. If checked, the program will sort these to an accessible location for the user.

    **Note** The program also sorts blocks by colour and so colour coding blocks is a good way to know what each block does once it is compressed.

5. Upload your file and submit!

   Press browse and paste the path that you copied in step one and press submit.

6. Download your compressed blueprint file.

   A new window will now pop up with 2 download options. Either download the file to the desired location (you can choose a new location or save it in the same blueprint you downloaded from) or copy the raw json text and paste it into the original blueprint.json.

   You're done! Open Scrap Mechanic, load your blueprint and enjoy. 
   
