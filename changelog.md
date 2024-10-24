# 1.3.0
### Additions
- Added new Coffee feature. Obtain Coffee Beans by finding Wild Coffee in the jungle or trading with villagers.
  New items include:
  - Wild Coffee, a plant found in the jungle that drops Coffee Beans
  - Coffee Beans, used to grow coffee crops
  - Roasted Coffee Beans, used to brew coffee
  - Golden Coffee Beans, used to brew a Potion of Haste
  - Bag of Coffee Beans
  - Coffee-Braised Beef
- Added drinkable Coffee items. Drink them to gain Speed and Haste for a short while. 
  Some coffee variants have additional effects! New items include:
  - Coffee
  - Milk Coffee
  - Chocolate Coffee
  - Honey Coffee
  - Dark Coffee
- Added Haste potions, including long and strong variants. Brew them with Golden Coffee Beans.
  - Potion of Haste
  - Splash Potion of Haste
  - Lingering Potion of Haste
  - Arrow of Haste
- Added new configuration options
  - Wild Coffee chance, used to configure Wild Coffee spawning. Set to zero to disable the Coffee feature entirely (including trades).
  - Enable villager trades
  - Enable wandering trader trades
  - Enable potions (currently enables brewing a Potion of Haste)
- Added alternative recipes for some Farmer's Delight items
  - Fried Egg can now be obtained by putting an Egg and Cooking Oil in a Cooking Pot
  - Fried Rice can now also be cooked by using Cooking Oil instead of an Egg

### Changes
- Improved tooltips to include status effects
- Wild Cotton and Wild Bell Peppers can now be obtained using Shears
- Batter and Bell Pepper Soup now use the eating sound when consumed, to be more consistent with other soup-like items
- Tweaked Raw Calamari food values

### Fixes
- Fixed pancake blocks not dropping a bowl when the last serving was eaten
- Fixed compatibility with Serene Seasons


# 1.2.1
### Changes
- Recipes using Cooking Oil now use the `cooking_oil` tag instead of the item

### Fixes
- Fixed server crashing when loading Rustic Delight (sorry!)


# 1.2.0

### Additions
- Added Cherry Blossom food items
  - Cherry Blossom Pancakes
  - Cherry Blossom Cheesecake
  - Slice of Cherry Blossom Cheesecake
  - Cherry Blossom Cookie
  - Cherry Blossom Roll
- Added more mod integration for Villagers
  - Villagers can now plant Cotton and Bell Peppers
  - Added Red Bell Pepper trade to Farmer villager (level 1)
  - Added Bell Pepper Seeds trade to Wandering Trader
- Added mod configuration options
  - Added configuration option `squids_drop_calamari` to disable squids droppping Calamari and remove calamari villager trades
  - Added configuration option `wild_cotton_chance` to configure chance of Cotton generating in the world. 
    Set this to zero if you want to remove obtaining Cotton entirely (including villager trades).
  - Added configuration option `wild_bell_peppers_chance` to configure chance of Bell Peppers generating in the world.
   Set this to zero if you want to remove obtaining Bell Peppers entirely (including villager trades).
- Added new tags
  - Added `cherry_blossom_ingredients` tag for Cherry Blossom-based food items
  - Added `cooking_oil` tag for better compatability with other mods adding oil
  - Added `calamari_roll_ingredients` tag for better compatability with other mods adding calamari
- Added language support for Simplified Chinese (Thank you Junnaturefox for providing the translations!)
- Added language support for Vietnamese (Thank you GodOfLac for providing the translations!)

### Changes
- Added `bell_pepper_seeds_bag` to the `farmersdelight:straw_blocks` tag
- Changed maximum stack size for pancakes and plated dishes from 64 to 16
- Rebalanced Wandering Trader trades
- Rebalanced wild crop spawn rates
- Pancake blocks now output an analog signal depending on the amount of pancakes left

### Fixes
- Fixed pancake blocks being allowed to be placed anywhere: They can now only be placed on solid blocks.

# 1.1.0

### Additions
- Added a new Bell Pepper crop, with associated blocks and items
- Added edible Pancake blocks
- Added numerous food recipes

### Changes
- Rebalanced Potato Slices
- Improved item tags


# 1.0.0

### Additions
- Added Wild Cotton, Cotton Boll, Cotton Seeds, Cotton Boll Crate, Bag of Cotton Seeds
- Added Potato Slices, Baked Potato Slices
- Added Calamari, Cooked Calamari, Calamari Slice, Cooked Calamari Slice, Calamari Roll
- Added Cooking Oil