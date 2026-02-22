# 1.5.1
### Additions
- Added Korean translations (thank you TAILS!)

# 1.5.0
### Additions
- Added slices for Bell Peppers
  - Red Bell Pepper Slice
  - Green Bell Pepper Slice
  - Yellow Bell Pepper Slice
- Added slices for Roasted Bell Peppers
  - Roasted Red Bell Pepper Slice
  - Roasted Green Bell Pepper Slice
  - Roasted Yellow Bell Pepper Slice
- Added compatibility with Brewin' and Chewin'
  - Added Coaster models for most of Rustic Delight's items, allowing you to place them in your world!
  - Added Coaster models for the following Farmer's Delight items:
    - Wheat Dough
    - Dumplings
    - Cabbage Rolls
  - Use bell peppers as a crafting ingredient for Pizza
  - Use Cheese as a crafting ingredient for Stuffed Bell Pepper
- Added new tags: `spring_roll_ingredients` and `stuffed_bell_pepper_ingredients`
- Added Kazakh translations (thank you ninsent!)
- Added Russian translations (thank you MaxKuz67, mpustovoi & JedaiGames!)

### Changes
- Bell Pepper can now be used to cook: Spring Rolls, Cabbage Rolls
- Overridden some Farmer's Delight recipes to allow Potato Slices: Baked Cod Stew, Beef Stew, Vegetable Soup, Mushroom Rice
- Simplified Bell Pepper Pasta recipe to allow any combination of bell peppers
- Bell Pepper Roll now requires a Bell Pepper Slice, instead of a whole Bell Pepper
- Bell Pepper now restores 2 hunger points instead of 1
- Simplified Coffee and Dark Coffee recipes to only require Roasted Coffee Beans (you don't need a Water Bottle anymore)
- Simplified Honey Coffee recipe (you don't need to add Sugar anymore)
- Improved various textures
- Add biome tags to control spawning of Wild Bell Peppers, Wild Cotton and Wild Coffee
- Add missing translation keys for item tags, potted plants & crops

### Fixes
- Fix crop models for Bell Peppers and Coffee floating above the ground
  - They now use Farmer's Delight's `crop_cross` model

# 1.4.1
### Additions
- Added Spanish (Argentina) translations (thank you ex0planet!)

### Fixes
- Syrup now requires a Glass Bottle as a container, instead of a Bowl
- Rice Roll Royale now drops a bowl when empty (instead of a copy of itself, resulting in infinite sushi)

# 1.4.0
### Additions
- Overhauled the pancake feature:
  - Added ability to take a single pancake from a pancakes block, with a knife
  - Added Cooking Pot recipes for all pancakes
  - Added new pancake variants: Pancakes (the classic variant), Pumpkin Pancakes
  - Added Pancake, Honey Pancake, Chocolate Pancake, Vegetable Pancake, Cherry Blossom Pancake, Pumpkin Pancake
  - Rebalanced pancake recipes and gained effects
  - Updated all pancake models
- Added a new cooking ingredient: Syrup! Required for some new items:
  - Pancakes and Pumpkin Pancakes require Syrup to craft
  - Syrup Cheesecake
  - Syrup Cookie
  - Sweet Salad
- Added more Coffee-related foods:
  - Syrup Coffee
  - Coffee Cookie
- Added new Feast: Rice Roll Royale (requires one of each Rustic Delight rice roll item)
  - Added Green Bell Pepper Roll, Yellow Bell Pepper Roll, Red Bell Pepper Roll
- Added more fried foods
  - Fried Dough
  - Fried Dumplings

### Changes
- Wild Cotton now spawns more often, by default
- Cotton Boll can now be used as fuel
- Cooking Oil can now also be obtained with Pumpkin Seeds (in addition to Sunflower and Canola Seeds, if the proper mods are installed)
- Bell Pepper Soup is now crafted with 3 bell peppers instead of 4
- Updated item & block tags for better compatibility with other mods
- Rebalanced coffee effects
- Improved textures for most blocks and items


# 1.3.2
### Fixes
- Include conditional checks for Farmer's Respite and Botany Pots recipes, to fix parsing errors when the mods are not present.

# 1.3.1
### Additions
- Added Bag of Roasted Coffee Beans
- Added Japanese translations (thank you EndilCrafter!)
- Added compatibility with Botany Pots:
  - Cotton, Coffee and Bell Peppers can now be grown in a Botany Pot
  - Wild Cotton, Wild Coffee and Wild Bell Peppers are also supported
- Added compatibility with Farmer's Respite:
  - Brew Farmer's Respite Coffee with Rustic Delight's Roasted Coffee Beans using a Kettle
  - Rustic Delight Coffee (and variants) can be brewed with Farmer's Respite Coffee Beans using the Cooking Pot
  - Rustic Delight Coffee variants can be crafted using Farmer's Respite Coffee
- Added compatibility with Supplementaries:
  - Cherry Blossom Cookie can now be put in a jar
  
### Changes
- Golden Coffee Beans can now also be crafted with Roasted Coffee Beans
- Roasted Coffee Beans can now be crafted into Brown Dye
- Made the following items compostable
  - Cherry Blossom Cookie
  - Cherry Blossom Cheesecake
  - Slice of Cherry Blossom Cheesecake
- Updated textures for Coffee Beans, Roasted Coffee Beans, Coffee Beans Bag

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
- Added German translations

### Changes
- Improved tooltips to include status effects
- Wild Cotton and Wild Bell Peppers can now be obtained using Shears
- Batter and Bell Pepper Soup now use the eating sound when consumed, to be more consistent with other soup-like items
- Tweaked Raw Calamari food values
- Cherry Blossom Cheesecake can now be cut into slices using the Cutting Board

### Fixes
- Fixed pancake blocks not dropping a bowl when the last serving was eaten
- Fixed compatibility with Serene Seasons


# 1.2.0

### Additions
- Added Cherry Blossom food items
    - Cherry Blossom Pancakes
    - Cherry Blossom Cheesecake
    - Slice of Cherry Blossom Cheesecake
    - Cherry Blossom Cookie
    - Cherry Blossom Roll
- Added mod configuration options
    - Configuration option `squids_drop_calamari` now also removes calamari villager trades
    - Added configuration option `wild_cotton_chance` to configure chance of Cotton generating in the world.
      Set this to zero if you want to remove obtaining Cotton entirely (including villager trades).
    - Added configuration option `wild_bell_peppers_chance` to configure chance of Bell Peppers generating in the world.
      Set this to zero if you want to remove obtaining Bell Peppers entirely (including villager trades).
- Added new tags
    - Added `cherry_blossom_ingredients` tag for Cherry Blossom-based food items
    - Added `cooking_oil` tag for better compatibility with other mods adding oil
- Added mod compatibility for Frycook's Delight
- Added language support for Vietnamese (Thank you GodOfLac for providing the translations!)

### Changes
- Pancake blocks now output an analog signal depending on the amount of pancakes left

### Fixes
- Fixed pancake blocks being allowed to be placed anywhere: They can now only be placed on solid blocks.
- Fixed stack size of Potato Salad


# 1.1.2

### Additions
- Add configuration option `squidsDropCalamari` to disable squids droppping Calamari
- Add mod compatibility for Cultural Delights and Miner's Delight

### Changes
- Add `bell_pepper_seeds_bag` to the `farmersdelight:straw_blocks` tag


# 1.1.1

### Additions
- Added language support for Simplified Chinese (Thank you Junnaturefox for providing the translations!)
- Added Forge 1.20.1 support

### Changes
- Changed maximum stack size for pancakes and plated dishes from 64 to 16
- Villagers can now plant Cotton and Bell Peppers
- Added Red Bell Pepper trade to Farmer villager (level 1)
- Added Bell Pepper Seeds trade to Wandering Trader
- Rebalanced Wandering Trader trades
- Rebalanced wild crop spawn rates

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