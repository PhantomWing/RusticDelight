# 1.7.1
### Changes
- Updated Japanese translations (thank you HayaKoh!)

### Fixes
- Fixed Squids & Glow Squids not dropping Calamari


# 1.7.0
From colorful bell peppers to cozy cups of coffee, Rustic Delight 1.7 is packed with vibrant new bell pepper varieties, comforting recipes, world generation improvements, and plenty of delightful surprises waiting to be discovered.

### Additions
- Added mutated variants of the bell pepper crop
  - **Pale Bell Pepper Seeds**: Yields white, pink and orange bell peppers
  - **Dark Bell Pepper Seeds**: Yields black, blue and purple bell peppers
- Added **Wild Pale Bell Peppers** and **Wild Dark Bell Peppers**, which can be found in jungles (rare)
- Added **Bell Pepper Medley**, a new feast crafted with 1 of each stuffed bell pepper color
- Added **Giant Bell Pepper** block for each color, crafted with Bell Pepper Slice and randomly found in jungles (rare)
  - Configurable with the `bell_pepper_block_patch_chance` config option (set to zero to turn off spawning)
- Added two new types of coffee: **Pumpkin Coffee** and **Cherry Blossom Coffee**
- Added **Fried Fish**: new fried food crafted with any safe fish, Batter and Cooking Oil
- Added **Coffee Cheesecake** and **Slice of Coffee Cheesecake**
- Added **Coffee Pancakes** and **Coffee Pancake**
  - In addition, you can now add additional pancakes onto a default pile of 6, by shift-right-clicking while holding a pancake (to a maximum of 12).
- Added **Calamari Soup**
- Added **Calamari Crate**, a storage block for Calamari
- Rustic Delight's crops now randomly spawn in village farms (turn off with the `generate_village_farm_crops` config option)
- Rustic Delight's items now randomly generate as loot in chests (turn off with the `generate_random_loot` config option)
- Added advancements to guide new players through the features of Rustic Delight (gated by the various feature toggles)
- Added new feature toggles to the config (so you can disable features, making related items unobtainable)
  - `enable_bell_peppers`
  - `enable_cherry_blossom_foods`
  - `enable_coffee`
  - `enable_cotton`
  - `enable_fried_foods`
  - `enable_pancakes`
  - `enable_potato_slices`
  - `enable_syrup_foods`
- Added 3D models for many of the new items added in this update (requires Brewin' and Chewin' coaster)
- Added Ukrainian translations (thank you hikarosato!)

### Changes
- You no longer need a knife to get a single pancake: Pancakes are now taken from the block by right-clicking, instead of being eaten directly.
- Breaking a pancake block now drops the remaining pancakes as individual pancake items
- Updated Rice Roll Royale texture & models to match Farmer's Delight latest Rice Roll Medley changes
- Rebalanced cheesecake recipes
- Improved Cherry Blossom Pancakes model
- Improved various textures, such as Cotton Boll, Wild Bell Peppers, Coffee Beans
- Villagers can now consume Rustic Delight crops, making them compatible for automated villager farms
- Wandering traders now rarely offer Pale and Dark Bell Pepper Seeds
- Pancake block models are now named after the number of pancakes on the stack (`<flavour>_stack_6` rather than `<flavour>_stage0`), which resource packs targeting them will need to follow

### Fixes
- Fixed bell peppers dropping only 1 slice when cut, instead of 2
- Potion of Haste is now brewed from an Awkward Potion rather than straight from a Water Bottle, matching every other potion


# 1.6.1
### Changes
- Lowered the minimum required dependency versions so the mod works without updating everything to the latest:
  - Farmer's Delight Refabricated 3.6.9 (was 3.6.10)
  - Fabric Loader 0.19 (was 0.19.3)


# 1.6.0
### Changes
- Updated Farmer's Delight Refabricated to 3.6.10
- Improved wild crop generation to include Coarse Dirt and bushes
- Rebalanced/simplified Coffee recipes and effects
  - Coffee now requires 3 coffee beans, instead of 4
  - Chocolate Coffee now requires 1 cocoa beans instead of 2
  - Chocolate & Honey Coffee effects now last 1:00 instead of 0:45
- Added a "Placeable" tooltip on placeable food blocks (Syrup Cheesecake, Cherry Blossom Cheesecake, all pancake blocks, Rice Roll Royale)
- Added particles to Pancake blocks when eating/taking a serving
- Added Chilean Spanish translations (thank you vlaster666!)

### Fixes
- Fixed (Roasted) Bell Peppers only dropping 1 slice instead of 2 when cut with a knife
- Fixed Syrup Coffee having the same effects as Milk Coffee
- Fixed Copper Knife not being recognized as a knife (temporary fix until FDR adds it)
- Fixed missing textures on Coffee/Bell Pepper crops and Cheesecake blocks after Farmer's Delight renamed its shared models
- Fixed coffee tooltips not showing after Farmer's Delight renamed its tooltip translation keys


# 1.5.4
### Additions
- Updated Korean translations (thank you TAILS!)

# 1.5.3
### Changes
- Calamari drops from Squids & Glow Squids are now affected by Looting enchantment.

### Fixes
- Fix Squids & Glow Squids never dropping Calamari if the LootTable is already overridden by another mod, regardless of configuration settings.

# 1.5.2
### Fixes
- Fix farmers not being able to consume Rustic Delight crops or trade them with other villagers.


# 1.5.1
### Additions
- Added Spanish (Argentina) translations (thank you ex0planet!)
- Added Korean translations (thank you TAILS!)

### Fixes
- Added Cloth Config as a dependency
- Added rusticdelightrefabricated to the "breaks" list in fabric.mod.json to prevent conflicts with (unofficial) Rustic Delight Refabricated


# 1.5.0
### Additions
- Added configuration options (JSON file)
  - `squids_drop_calamari` to disable squids droppping Calamari and remove calamari villager trades
  - `wild_cotton_chance`, used to configure Wild Cotton spawning. Set to zero to disable the Cotton feature entirely (including trades).
  - `wild_bell_peppers_chance`, used to configure Wild Bell Peppers spawning. Set to zero to disable the Bell Pepper feature entirely (including trades).
  - `wild_coffee_chance`, used to configure Wild Coffee spawning. Set to zero to disable the Coffee feature entirely (including trades).
  - `enable_villager_trades`
  - `enable_wandering_trader_trades`
  - `enable_potions`(currently enables brewing a Potion of Haste)
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
- Added compatibility with Botany Pots
  - Added support for Cotton, Coffee and Bell Peppers, Wild Cotton, Wild Coffee and Wild Bell Peppers
- Added new tags: `spring_roll_ingredients` and `stuffed_bell_pepper_ingredients`
- Added Kazakh translations (thank you ninsent!)

### Changes
- Bell Pepper can now be used to cook: Spring Rolls, Cabbage Rolls
- Overridden some Farmer's Delight recipes to allow Potato Slices: Baked Cod Stew, Beef Stew, Vegetable Soup, Mushroom Rice
- Added Potato Slices to the `crops/potato` tag, for better compatibility with other mods
- Simplified Bell Pepper Pasta recipe to allow any combination of bell peppers
- Bell Pepper Roll now requires a Bell Pepper Slice, instead of a whole Bell Pepper
- Bell Pepper now restores 2 hunger points instead of 1
- Improved various textures
- Added biome tags to control spawning of Wild Bell Peppers, Wild Cotton and Wild Coffee
  - Wild Cotton now also spawns in biomes tagged with `#c:is_forest` (in addition to `#minecraft:is_forest`)
  - Wild Bell Peppers & Wild Coffee now also spawn in biomes tagged with `#c:is_jungle` (in addition to `#minecraft:is_jungle`)
- Added missing translation keys for item tags, potted plants & crops
- Added Bell Pepper Soup to the `#c:foods/soup` tag

### Technical Changes
- Implemented data generation for easier future updates
- Updated versions for some dependencies

### Fixes
- Fix crop models for Bell Peppers and Coffee floating above the ground
  - They now use Farmer's Delight's `crop_cross` model
- Fixed cutting recipe for Baked Potato (yielded Potato Slices instead of Baked Potato Slices)
- Fix incorrect Russian translations (thank you JedaiGames & mpustovoi!)


# 1.4.2
### Technical Changes
- Removed dependency on PortingLib
- Changed code to use parchment mappings, instead of yarn mappings

### Fixes
- Fix compatibility issues with Farmer's Delight Refabricated 3.0.0 and onwards
- Fix crash when drinking Milk Coffee, Chocolate Coffee, Honey Coffee or Syrup Coffee

# 1.4.1
### Additions
- Added Russian translations (thank you MaxKuz67!)

### Fixes
- Added farmersdelight dependency to fabric.mod.json
- Removed another-mod suggestion from fabric.mod.json
- Added missing translations for crop blocks
- Rice Roll Royale now drops a bowl when empty (instead of a copy of itself, resulting in infinite sushi)
- Syrup now requires a Glass Bottle as a container, instead of a Bowl


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


# 1.3.3

### Changes
- Bell Pepper Soup is now cooked with 3 bell peppers instead of 4, fixing a recipe conflict with Farmer's Delight Refabricated


# 1.3.2

### Fixes
- Removed data files for Botany Pots and Farmer's Respite, as those mods are not available for 1.21 yet and were causing parsing errors.

# 1.3.1

### Additions
- Added Bag of Roasted Coffee Beans
- Added Japanese translations (thank you EndilCrafter!)
- Added Cherry Blossom Cookie to the `c/foods/cookie` tag

### Changes
- Changed mod version to match Forge/NeoForge version
- Golden Coffee Beans can now also be crafted with Roasted Coffee Beans
- Roasted Coffee Beans can now be crafted into Brown Dye
- Made the following items compostable
  - Cherry Blossom Cookie
  - Cherry Blossom Cheesecake
  - Slice of Cherry Blossom Cheesecake
- Updated textures for Coffee Beans, Roasted Coffee Beans, Bag of Coffee Beans
 
# 1.0.2

### Additions
- Added Taiwanese Mandarin translations (thank you Lobster0228!)
- Villagers can now farm Rustic Delight crops

### Fixes
- Fixed squids and glow squids not dropping Calamari


# 1.0.1

### Additions
- Cherry Blossom Cheesecake can now be cut into slices using the Cutting Board
- Added German translations

### Fixes
- Fixed compatibility with Minecraft 1.21


# 1.0.0

### Additions
- First port to Fabric. Some features from the NeoForge version might still be missing and will be added at a later stage.