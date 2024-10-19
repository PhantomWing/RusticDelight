# 1.3.0
### Additions
- Added new Coffee crop
  - Obtain Coffee Beans by finding Wild Coffee in the jungle or trading with villagers


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