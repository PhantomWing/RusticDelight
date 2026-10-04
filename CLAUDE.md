@~/Documents/Projects/Minecraft/MinecraftDeveloperPortal/.claude/profiles/fabric-loom.md

# Rustic Delight - `fabric/1.21.4`

**This file describes the `fabric/1.21.4` line**: Minecraft 1.21.4, Fabric.
Built with Fabric Loom.

It belongs to whichever folder has this branch checked out - the main `RusticDelight` folder or a worktree
under `RusticDelight/.worktrees/`. A session started in a worktree also loads the main folder's CLAUDE.md,
which describes another line; for this folder, this file is the one that applies. Confirm with
`git branch --show-current`. `MinecraftDeveloperPortal/data/mods.json` lists every line of the mod.

A rustic add-on for Farmer's Delight: coffee, cotton, bell peppers, pancakes, calamari and more.
Mod ID `rusticdelight`.

Split build: the Fabric lines are `fabric/*` (Loom); the NeoForge and Forge lines are `version/*`
(ModDevGradle, ForgeGradle), single-loader despite the prefix.

On the Fabric lines, Farmer's Delight Refabricated comes from the Modrinth maven as `<mc>-<fdr>`,
published against the base MC version (`dependency-sources.md`). REI and JEI availability lags the ladder; run
`/mc-check-deps` before assuming one exists for a rung.

## This line

- Java 21, Mojang mappings with Parchment.
- Datagen: `runDatagen`. Output: `src/main/generated/resources`, never hand-edited. **Not pinned with `modId = mod_id`**: check its diff for files in the `minecraft` namespace (`fabric-loom.md`).
- No game tests yet. Writing the first one for whatever is ported next is the highest-value test available (`verification.md`).
- No `publishMods` on this line: add the block before publishing it through the plugin (`publishing.md`).
- GitHub Actions: `build.yml`.
